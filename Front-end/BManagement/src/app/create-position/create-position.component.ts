import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Position 
} from '../Entities/Position';
import { PositionServiceService } from '../position-service.service';


@Component({
  selector: 'app-create-position',
  templateUrl: './create-position.component.html',
  styleUrls: ['./create-position.component.css']
})
export class CreatePositionComponent implements OnInit {
  position: Position = new Position();
  public message:string ="";
  
  constructor(private service: PositionServiceService,private router: Router) { }

  ngOnInit(): void {
  }

  savePosition(){
    this.service.addPosition(this.position).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
  }

  goToList(){
    this.router.navigate(["/positions"]);
  }

  onSubmit(){
    if(this.position.name.trim()!=""&&
    this.position.code.trim()!=""&&
    this.position.description.trim()!=""){
      this.savePosition();
      
    }else{
      this.message = "Please enter valid input";
    }
    console.log(this.position);
  }
}
