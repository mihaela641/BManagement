import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Manufacturer } from '../Entities/Manufacturer';
import { Product } from '../Entities/Product';
import { Type } from '../Entities/Type';
import { ManufacturerService } from '../manufacturer.service';
import { ProductService } from '../product.service';
import { TypeService } from '../type.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {
  product:Product = new Product();
  id:number = 0;
  message:string="";

  manufacturers:Manufacturer[] = new Array();
  types:Type[] = new Array();

  constructor(private service:ProductService, private router:Router
    , private manufacturerService:ManufacturerService
    ,private typeService:TypeService
    ,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getProduct(this.id).subscribe(data=>{
      this.product = data;
      console.log(data);
    });
    this.getTypes();
    this.getManufacturers();
  }

  getManufacturers(){
    this.manufacturerService.getManufacturersArray().subscribe(data=>this.manufacturers=data);
  }

  getTypes(){
    this.typeService.getTypesArray().subscribe(data=>this.types=data);
  }

  onSubmit(){
      if(this.product.name.trim()!=""
      &&this.product.dateAdded.toString().trim()!=""
      &&this.product.manufacturer.name.trim()!=""
      &&this.product.price>0
      &&this.product.type!=undefined){
        this.service.updateProduct(this.product,this.id).subscribe(data=>{
          this.router.navigate(["/products"]);
        },error=>console.error(error)
        );
       }else{
         this.message = "Please enter valid input";
       }
  }
}
