import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Position } from '../Entities/Position';
import { PositionServiceService } from '../position-service.service';

@Component({
  selector: 'app-update-position',
  templateUrl: './update-position.component.html',
  styleUrls: ['./update-position.component.css']
})
export class UpdatePositionComponent implements OnInit {
  position:Position = new Position();
  id:number = 0;
  constructor(private service:PositionServiceService,private route:ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getPosition(this.id).subscribe(data=>{
      this.position = data;
      console.log(data);
    });
  }

  onSubmit(){
    this.service.updatePosition(this.position,this.id).subscribe(data=>{
      this.router.navigate(["/positions"]);
    },error=>console.error(error)
    );
  }
}
