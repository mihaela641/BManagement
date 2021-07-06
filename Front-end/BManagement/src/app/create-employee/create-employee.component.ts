import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { Country } from '../Entities/Country';
import { Employee } from '../Entities/Employee';
import { Position } from '../Entities/Position';
import { Shop } from '../Entities/Shop';
import { EnumService } from '../enum.service';
import { PositionServiceService } from '../position-service.service';
import { ShopService } from '../shop.service';


@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  message:string = "";
  employee:Employee = new Employee();

  positions:Position[] = new Array();
  shops:Shop[] = new Array();
  countries:Country[] = new Array();

  inputCountry:Country = new Country();
  inputShop:Shop = new Shop();
  inputPosition:Position = new Position();

  constructor(private service: EmployeeService,private router: Router
    ,private enumService:EnumService
    ,private shopService:ShopService
    ,private positionService:PositionServiceService) { }

  ngOnInit(): void {
    this.getCountries();
    this.getShops();
    this.getPositions();
    console.log(this.shops);
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

  goToList(){
    this.router.navigate(["/employees"]);
  }


  saveEmployee(){
    this.service.addEmployee(this.employee).subscribe(data=>{
      console.log(data);
    },error => console.error(error));
    this.goToList();
  }
  onSubmit(){
      this.employee.position = this.inputPosition;
      this.employee.country = this.inputCountry;
      this.employee.shop = this.inputShop;
   if(this.employee.name.trim()!=""){
      this.saveEmployee();
    }else{
      this.message = "Please enter valid input";
    }
  }
}
