package com.shanxiut.scs.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 代码维护表
 * Created by hliu on 2019/4/28.
 */
@Entity
@Table(name = "scs_code_library")
@Data
public class CodeLibrary extends SuperEntity <CodeLibrary>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codeNo;

    private String codeName;

    private String itemNo;

    private String itemName;
}
