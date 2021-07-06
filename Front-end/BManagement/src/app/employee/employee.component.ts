import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Employee } from '../Entities/Employee';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employees: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';
  
  name:string = '';
  dateFrom:string = '';
  dateTo:string = '';
  positionId = 0;
  countryId = 0;
  salary = 0;
  shopId = 0;

  inputName:string ="";
  inputShop:string = "";
  inputDateFrom:string="";
  inputDateTo:string ="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
  

  constructor(private service:EmployeeService, private router:Router) {
    this.employees = new Array<any>();
  }

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(){
    console.log(this.params);
    this.service.getEmployees(this.params).subscribe(data=>{
      console.log(data);
      this.employees=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteEmployee(id:number){
    this.service.deleteEmployee(id).subscribe(data=>{
      console.log(data);
      this.getEmployees();
    },error=>console.log(error));
  }

  updateEmployee(id:number){
    this.router.navigate(["/update-employee",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getEmployees();
  }

  sort(by:any){
    if(by=="id"){
      this.params = this.params.set('sortBy',"id");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="name"){
      this.params = this.params.set('sortBy',"name");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="country"){
      this.params = this.params.set('sortBy',"country");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="position"){
      this.params = this.params.set('sortBy',"position");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="salary"){
      this.params = this.params.set('sortBy',"salary");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="shop"){
      this.params = this.params.set('sortBy',"shop");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="date"){
      this.params = this.params.set('sortBy',"birthDate");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    
    this.getEmployees();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputShop.trim()!=""){
      this.params = this.params.set('shop',this.inputShop);
    }
    if(this.inputDateFrom.trim()!=""){
      this.params = this.params.set('dateFrom',this.inputDateFrom);
    }
    if(this.inputDateTo.trim()!=""){
      this.params = this.params.set('dateTo',this.inputDateTo);
    }
    
    this.getEmployees();
  }

  clear(){
    console.log(this.params);
    this.params = this.params.delete('shop');
    this.params = this.params.delete('name');
    this.params = this.params.delete('dateFrom');
    this.params = this.params.delete('dateTo');
    this.inputName ="";
    this.inputShop = "";
    this.inputDateFrom = "";
    this.inputDateTo = "";
    this.getEmployees();
  }
}
