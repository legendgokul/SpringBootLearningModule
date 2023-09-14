package com.example.FirstApp;
import java.util.*;
//import com.example.FirstApp.student.DummyService;
import com.example.FirstApp.student.IStudentRepository;
import com.example.FirstApp.student.Student;
import com.example.FirstApp.student.studentModel;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/Student")
public class DummyController {

    //private final DummyService _dummyService;
    private final IStudentRepository _studRepo;

    //@Autowired
    public DummyController(/*DummyService dummyService, */IStudentRepository studentRepository){
        //this._dummyService = dummyService;
        this._studRepo = studentRepository;
    }

    @GetMapping(value="/getAll")
    public List<Student> GetAllStudent(){
        var Data = _studRepo.findAll();
        return Data;
    }
    @GetMapping(value = "/GetStudentforID/{id}")
    public Optional<Student> findOneStudent(@PathVariable ("id") UUID id)
    {
        return _studRepo.findById(id);
    }
    @RequestMapping(value = "/GetStudentListWithID", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> GetStudentListWithID(@RequestHeader("IDlist") List<UUID> studentIDlist){
        List<Student> data = _studRepo.findAllById(studentIDlist);
        return ResponseEntity.ok(data);
    }
    @PostMapping(value="/Add")
    public Student InsertStudent(@RequestBody Student newStud){
            return  _studRepo.save (newStud);
    }
    @PostMapping(value="/AddALL")
    public List<Student> InsertStudent(@RequestBody List<Student> newStud){
            return  _studRepo.saveAll(newStud);
    }
    @RequestMapping(value="/DeleteWithID",method = RequestMethod.DELETE)
    public ResponseEntity<Void> DeleteWithID(@RequestHeader("DeleteID") List<UUID> studid){
        _studRepo.deleteAllById(studid);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value="/UpdateWithID/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Boolean> UpdateWithID(@PathVariable("id") UUID studid,@RequestBody studentModel newStud){

        Optional<Student> Data = _studRepo.findById(studid);
        if(Data.isPresent()){
            //data present
            Student S = Data.get();
            S.setName(newStud.getName());
            S.setEmail(newStud.getEmail());
            S.setAge(newStud.getAge());
            try {
                _studRepo.save(S);
                return ResponseEntity.ok(true);
            }catch(Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }else {
            //data not present
            return ResponseEntity.ok(false);
        }
    }



}
