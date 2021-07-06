import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Manufacturer } from '../Entities/Manufacturer';
import { ManufacturerService } from '../manufacturer.service';

@Component({
  selector: 'app-update-manufacturer',
  templateUrl: './update-manufacturer.component.html',
  styleUrls: ['./update-manufacturer.component.css']
})
export class UpdateManufacturerComponent implements OnInit {
  manufacturer:Manufacturer = new Manufacturer();
  id:number = 0;
  constructor(private service:ManufacturerService,private route:ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getManufacturer(this.id).subscribe(data=>{
      this.manufacturer = data;
      console.log(data);
    });
  }

  onSubmit(){
    this.service.updateManufacturer(this.manufacturer,this.id).subscribe(data=>{
      this.router.navigate(["/manufacturers"]);
    },error=>console.error(error)
    );
  }

}
