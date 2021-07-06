import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-tag',
  templateUrl: './tag.component.html',
  styleUrls: ['./tag.component.css']
})
export class TagComponent implements OnInit {

  tags: Array<any>;
  page:number = 1;
  items:number = 0;
  itemsPerPage:number= 10;
  sortBy:string = '';
  sortDirection:string = '';

  name:string = '';
  code: string = '';
  
  inputName = "";
  inputCode="";

  params:HttpParams = new HttpParams().append('page', this.page)
  .append('size', this.itemsPerPage)
 

  constructor(private service:TagService, private router:Router) { 
    this.tags = new Array<any>();
  }

  ngOnInit(): void {
    this.getTags();
   }
 
   getTags(){
     this.service.getTags(this.params).subscribe(data=>{
       this.tags=data['content'];
       this.items = data['totalElements'];
     });
   }
 
   deleteTag(id:number){
     this.service.deleteTag(id).subscribe(data=>{
       this.getTags();
     },error=>console.log(error));
   }
 
   updateTag(id:number){
     this.router.navigate(["/update-tag",id]);
   }
 
   changePage(event:any){
     this.page=event;
     this.params= this.params.set('page',event);
     this.getTags();
   }
 
   search(){
     if(this.inputName.trim()!=""){
       this.params = this.params.set('name',this.inputName);
     }
     if(this.inputCode.trim()!=""){
       this.params = this.params.set('code',this.inputCode);
     }
     this.getTags();
     console.log(this.tags);
   }
 
   clear(){
     this.params = this.params.delete('code');
     this.params = this.params.delete('name');
     this.inputCode="";
     this.inputName="";
     this.getTags();
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

    this.getTags();
  }
}
