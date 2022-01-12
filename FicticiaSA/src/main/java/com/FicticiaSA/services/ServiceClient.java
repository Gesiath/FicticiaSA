package com.FicticiaSA.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.FicticiaSA.entities.Client;
import com.FicticiaSA.exceptions.ServiceException;
import com.FicticiaSA.repositories.RepositoryClient;

@Service
public class ServiceClient implements UserDetailsService{

	@Autowired
	private RepositoryClient repoClient;
	
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		
		try {
			Client client = repoClient.findByEmail(mail);
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			authorities.add(new SimpleGrantedAuthority("ROLE_" + client.getRol()));
			
			// Se extraen atributos de contexto del navegador
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

			// Se crea la sesion y se agrega el cliente a la misma
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usersession", client);
			
			return new User(mail, client.getPassword(), authorities);
		} catch(Exception e) {
			
			throw new UsernameNotFoundException("El usuario no existe");
			
		}
	}
	
	//REGISTRO CLIENTE NUEVO
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
	public Client registrationClient(String email, String password, String fullName, Long dni,
									 String address, String birthday, Long phone, Boolean driver, 
									 Boolean glasses, Boolean diabetic, String typeDiabetes, 
									 Boolean diseases, String whatDiseases) throws ServiceException {
		
		validation(email);
		
		Client client = new Client();
		
		client.setEmail(email);
		String encriptedPass = new BCryptPasswordEncoder().encode(password);
		client.setPassword(encriptedPass);
		client.setFullName(fullName);
		client.setDni(dni);
		client.setAddress(address);
		client.setBirthday(birthday);
		client.setPhone(phone);
		client.setDriver(driver);
		client.setGlasses(glasses);
		client.setDiabetic(diabetic);
		client.setTypeDiabetes(typeDiabetes);
		client.setDiseases(diseases);
		client.setWhatDiseases(whatDiseases);
		client.setActive(true);
		client.setRol("CLIENT");
		
		
		return repoClient.save(client);
	}
	
	
	//MODIFICAR DATOS CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Client modifyClient(String id, String fullName, Long dni,
			 String address, String birthday, Long phone, Boolean driver, 
			 Boolean glasses, Boolean diabetic, String typeDiabetes, 
			 Boolean diseases, String whatDiseases) throws ServiceException{
		
		Optional<Client> answer = repoClient.findById(id);
		if(answer.isPresent()) {
			
			Client client = answer.get();
			
			client.setFullName(fullName);
			client.setDni(dni);
			client.setAddress(address);
			client.setBirthday(birthday);
			client.setPhone(phone);
			client.setDriver(driver);
			client.setGlasses(glasses);
			client.setDiabetic(diabetic);
			client.setTypeDiabetes(typeDiabetes);
			client.setDiseases(diseases);
			client.setWhatDiseases(whatDiseases);
			
			
			return repoClient.save(client);
		}else {
			throw new ServiceException("No se encontro el cliente solicitado.");
		}		
		
	}
	
	//DAR DE ALTA O BAJA CLIENTE
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void setActive(String id) {
		
		Optional<Client> answer = repoClient.findById(id);
		if (answer.isPresent()) {
			Client client = answer.get();
			if(client.getActive()) {
				client.setActive(false);
			} else {
				client.setActive(true);
			}
		}
	}
	
	//OBTENER POR ID
		@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
		public Client obtenerPorId(String id) throws ServiceException{
			
			Optional<Client> result  = repoClient.findById(id);
			
			if (result.isEmpty()) {
				throw new ServiceException("No se encontró el cliente");
			} else {
				return result.get();
			}
		}
	
		//VALIDAR
		public void validation(String email)throws ServiceException{
			
			if(repoClient.existByEmail(email)) {
				throw new ServiceException("Ya existe un usuario registrado con ese email.");
			}
		}
}
