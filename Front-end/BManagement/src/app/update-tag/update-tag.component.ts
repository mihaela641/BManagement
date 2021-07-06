import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tag } from '../Entities/Tag';
import { TagService } from '../tag.service';

@Component({
  selector: 'app-update-tag',
  templateUrl: './update-tag.component.html',
  styleUrls: ['./update-tag.component.css']
})
export class UpdateTagComponent implements OnInit {
  tag:Tag;
  id:number = 0;
  constructor(private service:TagService,private route:ActivatedRoute,private router: Router) { 
    this.tag = new Tag();
  }

  ngOnInit(): void {
    console.log(this.tag);
    this.id = this.route.snapshot.params['id'];
    this.service.getTag(this.id).subscribe(data=>{
      this.tag = data;
      console.log(data['isDeleted']);
    });
  }

  onSubmit(){
    console.log(this.tag);
    this.service.updateTag(this.tag,this.id).subscribe(data=>{
      this.router.navigate(["/tags"]);
    },error=>console.error(error)
    );
  }
}
