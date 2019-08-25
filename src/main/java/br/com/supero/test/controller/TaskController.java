package br.com.supero.test.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.supero.test.service.TaskService;
import br.com.supero.test.task.Task;

/**
 * Classe com os controllers REST da entidade Task
 */
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(path="/task")
public class TaskController {
	
	@Autowired
	TaskService taskService;	
	
	// metodo que retorna objeto por id, sem data de exclusão
	@GetMapping(path="/findOne/{id}")
	public @ResponseBody Task findOne(@PathVariable("id") Long id) {
		return taskService.findOne(id);		
	}
	
	// encontra todos os objetos, sem data de exclusão
	@GetMapping(path="/findAll")
	public @ResponseBody Iterable<Task> findAll() {
		return taskService.findAll();		
	}
	
	// insere uma data de exclusão para um objeto de id específico
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {		
		try {
			taskService.delete(id);
			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
	}
	
	// salva objeto e inclui novos objetos
	@RequestMapping(value = "/save", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long save(@RequestBody Task task, HttpServletResponse response) {
		Long id = 0L;
		try {
			id = taskService.save(task);
			response.setStatus(HttpStatus.OK.value());
		} catch (Exception e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return id;
	}

}