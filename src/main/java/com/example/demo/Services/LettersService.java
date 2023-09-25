package com.example.demo.Services;

import com.example.demo.Classes.LettersDto;
import com.example.demo.Repositories.LettersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;

@Service
public class LettersService {
    @Autowired
    private LettersRepository lettersRepository;
    private char[][] IntervalHandler(char[][] intervals){
        Arrays.sort(intervals, (a, b) ->
        {
            if (a[1] == b[1])
            {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        LinkedList<char[]> list = new LinkedList<>();
        for (char[] interval : intervals)
        {
            if (!list.isEmpty() && list.getLast()[1] >= interval[0])
            {

                while (!list.isEmpty() && list.getLast()[1] >= interval[0])
                {
                    interval[0] = (char) Math.min(list.getLast()[0], interval[0]);
                    interval[1] = (char) Math.max(list.getLast()[1], interval[1]);
                    list.removeLast();
                }
            }

            list.addLast(interval);
        }

        int pos = 0;
        char[][] answer = new char[list.size()][];
        for (char[] inteval : list)
        {
            answer[pos++] = inteval;
        }

        return answer;
    }
    public LettersDto min(){
        return lettersRepository.min();
    }
    public void save(char[][] intervals){
        char[][] dataForSave=IntervalHandler(intervals);
        for(int i =0;i<dataForSave.length;i++){
            lettersRepository.save(dataForSave[i][0],dataForSave[i][1]);
        }
    }
}