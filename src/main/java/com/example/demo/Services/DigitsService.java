package com.example.demo.Services;

import com.example.demo.Classes.DigitsDto;
import com.example.demo.Repositories.DigitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;

@Service
public class DigitsService {
    private int[][] IntervalHandler(int[][] intervals){
        Arrays.sort(intervals, (a, b) ->
        {
            if (a[1] == b[1])
            {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        LinkedList<int[]> list = new LinkedList<>();
        for (int[] interval : intervals)
        {
            if (!list.isEmpty() && list.getLast()[1] >= interval[0])
            {

                while (!list.isEmpty() && list.getLast()[1] >= interval[0])
                {
                    interval[0] = Math.min(list.getLast()[0], interval[0]);
                    interval[1] = Math.max(list.getLast()[1], interval[1]);
                    list.removeLast();
                }
            }

            list.addLast(interval);
        }

        int pos = 0;
        int[][] answer = new int[list.size()][];
        for (int[] inteval : list)
        {
            answer[pos++] = inteval;
        }

        return answer;
    }
    @Autowired
    private DigitsRepository digitsRepository;
    public DigitsDto min(){
        return digitsRepository.min();
    }
    public void save(int[][] intervals){
        int[][] dataForSave= IntervalHandler(intervals);
        char [][] temp= new char[intervals.length][2];
        for(int i =0;i<dataForSave.length;i++){
            for(int j=0;j<2;j++){
                temp[i][j]= (char) dataForSave[i][j];
            }
        }
        for(int i =0;i<dataForSave.length;i++){
            digitsRepository.save(dataForSave[i][0],dataForSave[i][1]);
        }
    }
}
