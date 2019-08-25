package br.com.supero.test.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.supero.test.task.Task;
import br.com.supero.test.task.TaskRepository;

/**
 * serviço de comunicação com o repository de Task
 */
@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;	

	public Task findOne(Long id) {
		return taskRepository.findOne(id);		
	}
	
	public Iterable<Task> findAll() {
		return taskRepository.findAll();		
	}
	
	public void delete(Long id) {
		taskRepository.setDeleteDate(id, new Date());		
	}
	
	public Long save(Task task) {
		taskRepository.save(task);
		return task.getId();
	}

}
