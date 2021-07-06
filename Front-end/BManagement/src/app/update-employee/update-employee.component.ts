import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Country } from '../Entities/Country';
import { Employee } from '../Entities/Employee';
import { Position } from '../Entities/Position';
import { Shop } from '../Entities/Shop';
import { EnumService } from '../enum.service';
import { PositionServiceService } from '../position-service.service';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {
  employee:Employee = new Employee();
  id:number = 0;

  positions:Position[] = new Array();
  shops:Shop[] = new Array();
  countries:Country[] = new Array();
  
  constructor(private service:EmployeeService,private route:ActivatedRoute,private router: Router,private enumService:EnumService
    ,private shopService:ShopService
    ,private positionService:PositionServiceService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getEmployee(this.id).subscribe(data=>{
      this.employee = data;
      console.log(data);
    });
    this.getCountries();
    this.getShops();
    this.getPositions();
  }

  getCountries(){
    this.enumService.getCountries().subscribe(data=>this.countries=data);
  }

  getPositions(){
    this.positionService.getPositionsArray().subscribe(data=>this.positions=data);
  }

  getShops(){
    this.shopService.getShopsArray().subscribe(data=>this.shops=data);
  }

  onSubmit(){
    this.service.updateEmployee(this.employee,this.id).subscribe(data=>{
      this.router.navigate(["/employees"]);
    },error=>console.error(error)
    );
  }
}
