package com.example.demo.Controllers;

import com.example.demo.Classes.DigitsDto;
import com.example.demo.Classes.LettersDto;
import com.example.demo.ControllerReturnable;
import com.example.demo.Services.DigitsService;
import com.example.demo.Services.LettersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/intervals")
public class MainController {
    private final DigitsService digitsService;
    private final LettersService lettersService;
    public MainController(DigitsService digitsService,LettersService lettersService){
        this.digitsService = digitsService;
        this.lettersService = lettersService;
    }
    @GetMapping(path="/min")
    @ResponseBody
    public ControllerReturnable min(String kind) {
        if(kind.equals("letters")){
            LettersDto lettersDto = lettersService.min();
            return lettersDto;
        }else{
            if(kind.equals("digits")){
                DigitsDto digitsDto=digitsService.min();
                return digitsDto;
            }
            else {
                return null;
            }
        }
    }
    @PostMapping("/merge")
    @ResponseBody
    public ResponseEntity merge(@RequestBody Object[][] intervals, String kind) {
        if(kind.equals("digits")){
            int [][] temp= new int[intervals.length][2];
            for(int i =0;i<intervals.length;i++){
                for(int j=0;j<2;j++){
                    temp[i][j]= (int) intervals[i][j];
                }
            }
            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            if(kind.equals("letters")){
                char [][] tempChar= new char[intervals.length][2];
                for(int i =0;i<intervals.length;i++){
                    for(int j=0;j<2;j++){
                        String tempString= intervals[i][j].toString();
                        tempChar[i][j]= tempString.charAt(0);
                    }
                }
                lettersService.save(tempChar);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
