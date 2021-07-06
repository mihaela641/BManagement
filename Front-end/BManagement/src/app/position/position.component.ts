import { HttpParams } from '@angular/common/http';
import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { PositionServiceService } from '../position-service.service';

@Component({
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['./position.component.css']
})

export class PositionComponent implements OnInit {

  positions: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;

  name:string = '';
  code: string = '';
  
  inputName = "";
  inputCode="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
 .append('name',this.name)
  .append('code', this.code);

  constructor(private service: PositionServiceService, private router: Router) {
    this.positions = new Array<any>();
   }

  ngOnInit(): void {
   this.getPositions();
  }

  getPositions(){
    console.log(this.params);
    this.service.getPositions(this.params).subscribe(data=>{
      console.log(data);
      this.positions=data['content'];
      this.items = data['totalElements'];
    });
  }

  deletePosition(id:number){
    this.service.deletePosition(id).subscribe(data=>{
      console.log(data);
      this.getPositions();
    },error=>console.log(error));
  }

  updatePosition(id:number){
    this.router.navigate(["/update-position",id]);
  }

  changePage(event:any){
    this.page=event;
    this.params= this.params.set('page',event);
    this.getPositions();
  }

  search(){
    if(this.inputName.trim()!=""){
      this.params = this.params.set('name',this.inputName);
    }
    if(this.inputCode.trim()!=""){
      this.params = this.params.set('code',this.inputCode);
    }
    this.getPositions();
  }

  clear(){
    this.params = this.params.delete('code');
    this.params = this.params.delete('name');
    this.inputCode="";
    this.inputName="";
    this.getPositions();
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
    if(by=="code"){
      this.params = this.params.set('sortBy',"code");
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

    this.getPositions();
  }
}
