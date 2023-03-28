package ru.skillbox.diplom.group35.microservice.admin_console.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "admin")
@RequiredArgsConstructor
public class Admin extends BaseEntity {
}
