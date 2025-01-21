package org.example.servlet_study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.servlet_study.entity.Board;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertBoardDto {

    private String title;
    private String content;


    public Board toBoard() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

}
