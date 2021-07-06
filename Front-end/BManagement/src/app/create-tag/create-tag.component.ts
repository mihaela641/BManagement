import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Tag } from '../Entities/Tag';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-create-tag',
  templateUrl: './create-tag.component.html',
  styleUrls: ['./create-tag.component.css']
})
export class CreateTagComponent implements OnInit {
  tag:Tag;
  public message:string ="";
  constructor(private service: TagService,private router: Router) {
    this.tag= new Tag();
   }

  ngOnInit(): void {
    console.log(this.tag);
  }

  saveTag(){
    this.service.addTag(this.tag).subscribe(data=>{
    },error => console.error(error));
    this.goToList();
  }

  goToList(){
    this.router.navigate(["/tags"]);
  }

  onSubmit(){
    if(this.tag.name.trim()!=""&&
    this.tag.code.trim()!=""){
      this.saveTag();
    }else{
      this.message = "Please enter valid input";
    }
  }
}
