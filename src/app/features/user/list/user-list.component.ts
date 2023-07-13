/*
 * Copyright(c) 2023 Luvina Software Company
 *
 * UserListComponent.component.ts , 6/30/2023 11:13 AM, LA31-AM-ThoPV
 */
import { Component,ViewChild, ElementRef } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { AppConstants } from "../../../app-constants";
import { UserService } from '../service/user.service';
import { ListUser } from '../model/list-user';
import { ListDepartment } from '../../department/model/list-department';
import { DepartmentService } from '../../department/service/department.service';
import { Condition } from '../model/condition';
import { UserUtils } from '../utils/user.utils';
import { Router } from '@angular/router';
import { Error } from 'src/app/shared/model/error';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
/**
 * Represents a component for managing users and departments.
 * 
 * @author [ThoPV]
 */
export class UserListComponent {
  constructor(
    public http: HttpClient,
    private userService: UserService,
    private departmentService: DepartmentService,
    private router: Router
  ) { }
  dataListUser: ListUser = new ListUser();
  dataListDepartment: ListDepartment = new ListDepartment();
  listCondition: Condition = this.userService.listCondition;
  totalPage: number = 0;
  displayPagination: boolean = true;
  sumEmployee: number = 0;
  messageSearch: string = "";
  errorMessage: Error = new Error();
  @ViewChild('inputSearchName', {static:false}) inputSearchName! : ElementRef;

  /**
   * Handles the change event of the department selection.
   * 
   * @param event 
   */
  changeIdDepartment(event: any) {
    this.listCondition.departmentId = event.target.value
  }

  /**
   * Performs a search based on the provided name and department ID.
   * 
   */
  onSearch() {
    this.listCondition.offset = 1
    this.listCondition.employeeName = UserUtils.removeWhiteSpace(this.listCondition.employeeName)

    this.getListUsers();
  }

  ngAfterViewInit(){
    this.inputSearchName?.nativeElement.focus();
  }

  /**
   * Initializes the component.
   * 
   */
  ngOnInit(): void {
    this.getListDepartments()
    this.getListUsers()

    // test call api auto inject token to header
    this.http.post(AppConstants.BASE_URL_API + "/test-auth", null)
      .subscribe({
        next: (response) => {
          console.log(response);
        },
        error: (error) => {
          console.log(error);
        },
        complete: () => {
          console.log('complete');
        }
      });
  };

  /**
   * Retrieves the list of users.
   * 
   */
  getListUsers() {
    this.userService.getListUsersCondition(this.listCondition).subscribe({
      next: (data) => {
        if (data.code == 200) {
          this.dataListUser = data as ListUser;
          this.getPaginationInfo();
          this.listCondition.employeeName = UserUtils.returnStrInitPercent(this.listCondition.employeeName)
          this.errorMessage = new Error()
        } else {
          this.errorMessage = data as Error
        }
      },
      error: (data) => {
        this.router.navigate(['error']);
      }
    })
  }

  /**
   * Retrieves the list of departments.
   * 
   */
  getListDepartments() {
    this.departmentService.getListDepartments().subscribe({
      next: (data) => {
        this.dataListDepartment = data;
      }
    })
  }

  /**
   * Retrieves pagination information.
   * 
   */
  getPaginationInfo() {
    this.sumEmployee = this.dataListUser.totalRecord;
    this.messageSearch = this.sumEmployee == 0 ? "検索条件に該当するユーザが見つかりません。" : ""

    if (this.sumEmployee <= 20) {
      this.displayPagination = false
    } else {
      this.displayPagination = true
      this.totalPage = Math.ceil(this.sumEmployee / this.listCondition.limit);
    }
  }

  /**
    * Generates an array of numbers from 1 to totalPage.
    * 
    * @param totalPage - The total number of pages.
    * @returns An array of numbers from 1 to totalPage. 
   */
  arrayTotalPageFirst(totalPage: number): number[] {
    return Array.from({ length: totalPage }, (_, i) => i + 1);
  }

  /**
   * Generates an array of numbers representing the last two pages.
   * 
   * @param totalPage - The total number of pages.
   * @returns An array of numbers representing the last two pages.
  */
  arrayTotalPageEnd(totalPage: number): number[] {
    return Array.from({ length: 2 }, (_, i) => totalPage - 2 + i);
  }

  /**
   * Navigates to the previous page.
   */
  toPrevPage() {
    this.listCondition.offset = this.listCondition.offset - 1
    this.getListUsers()
  }

  /**
   * Navigates to the specified page.
   * 
   * @param page - The page number to navigate to.
   */
  toPage(page: number) {
    this.listCondition.offset = page
    this.getListUsers()
  }

  /**
   * Navigates to the next page.
   * 
   */
  toNextPage() {
    this.listCondition.offset = this.listCondition.offset + 1
    this.getListUsers()
  }

  /**
   * Sorts the users based on the specified sorting order.
   * 
   * @param ordSort - The sorting order.
   */
  onSort(ordSort: string) {
    if (ordSort == 'ordEmployeeName') {
      this.listCondition.ordEmployeeName = this.listCondition.ordEmployeeName == 'ASC' ? 'DESC' : 'ASC'
      this.getListUsers()
    }
    if (ordSort == 'ordCertificationName') {
      this.listCondition.ordCertificationName = this.listCondition.ordCertificationName == 'ASC' ? 'DESC' : 'ASC'
      this.getListUsers()
    }

    if (ordSort == 'ordEndDate') {
      this.listCondition.ordEndDate = this.listCondition.ordEndDate == 'ASC' ? 'DESC' : 'ASC'
      this.getListUsers()
    }
  }
}
