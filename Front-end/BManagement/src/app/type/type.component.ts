import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TypeService } from '../type.service';

@Component({
  selector: 'app-type',
  templateUrl: './type.component.html',
  styleUrls: ['./type.component.css']
})
export class TypeComponent implements OnInit {

  types: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  name:string = '';
  code: string = '';
  parentType: string ="";
  
  inputName = "";
  inputCode="";
  inputParentType="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
  .append('sortBy',this.sortBy)
  .append('sortDirection', this.sortDirection)

  constructor(private service:TypeService, private router:Router) { 
    this.types = new Array<any>();
  }

  ngOnInit(): void {
    this.getTypes();
   }

   getTypes(){
    this.service.getTypes(this.params).subscribe(data=>{
      this.types=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteType(id:number){
    this.service.deleteType(id).subscribe(data=>{
      this.getTypes();
    },error=>console.log(error));
  } 

  updateType(id:number){
    this.router.navigate(["/update-type",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getTypes();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputCode.trim()!=""){
      this.params = this.params.set('code',this.inputCode);
    }
    if(this.inputParentType.trim()!=""){
      this.params = this.params.set('parentType',this.inputParentType);
    }
    this.getTypes();
    console.log(this.types);
  }

  clear(){
    this.params = this.params.delete('name');
    this.params = this.params.delete('code');
    this.params = this.params.delete('parentType');
    this.inputName="";
    this.inputCode="";
    this.inputParentType="";
    this.getTypes();
  }
}
