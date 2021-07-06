import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import  { ManufacturerService } from '../manufacturer.service';
import {MatDialog, MatDialogConfig} from '@angular/material/dialog';
import { CreateManufacturerComponent } from '../create-manufacturer/create-manufacturer.component';

@Component({
  selector: 'app-manufacturer',
  templateUrl: './manufacturer.component.html',
  styleUrls: ['./manufacturer.component.css']
})
export class ManufacturerComponent implements OnInit {

  manufacturers: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  nameChecked = false;
  addressChecked = false;
  inputName = "";
  inputAddress="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)


  constructor(private service: ManufacturerService,private router: Router,) {
    this.manufacturers = new Array<any>();
   }

  ngOnInit(): void {
    this.getManufacturers();
  }

  getManufacturers(){
    console.log(this.params);
    this.service.getManufacturers(this.params).subscribe(data=>{
      console.log(data);
      this.manufacturers=data['content'];
      this.items = data['totalElements'];
    });
  }

  deleteManufacturer(id:number){
    this.service.deleteManufacturer(id).subscribe(data=>{
      console.log(data);
      this.getManufacturers();
    },error=>console.log(error));
  }

  updateManufacturer(id:number){
    this.router.navigate(["/update-manufacturer",id]);
  }
  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getManufacturers();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputAddress.trim()!=""){
      this.params = this.params.set('address',this.inputAddress);
    }
    this.getManufacturers();
  }
 
  clear(){
    this.params = this.params.delete('name');
    this.params = this.params.delete('address');
    this.inputName="";
    this.inputAddress="";
    this.getManufacturers();
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
    if(by=="address"){
      this.params = this.params.set('sortBy',"address");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    if(by=="foundationDate"){
      this.params = this.params.set('sortBy',"foundationDate");
      if(this.params.get('sortDirection')=="asc"){
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"desc");
      }else{
        this.params.delete('sortDirection');
        this.params = this.params.set('sortDirection',"asc");
      }
    }
    
    this.getManufacturers();
  }

}
