/*
 * Copyright(c) 2023 Luvina Software Company
 *
 * DepartmentService.component.ts , 6/30/2023 11:13 AM, LA31-AM-ThoPV
 */


import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConstants } from 'src/app/app-constants';
import { ListDepartment } from '../model/list-department';
import { Department } from '../model/department';

@Injectable({
  providedIn: 'root'
})
/**
 * Represents a service for managing departments.
 * 
 * @author [ThoPV]
 */
export class DepartmentService {

  constructor(private httpClient: HttpClient) { }

  /**
   * Retrieves a list of departments.
   * 
   * @returns An Observable of type Department.
   */
  getListDepartments(): Observable<ListDepartment> {
    return this.httpClient.get(AppConstants.BASE_URL_API + '/department');
  }

  getDepartmentById(id : number) : Observable<Department> {
    return this.httpClient.get(AppConstants.BASE_URL_API + '/department/'+id)
  }
}
