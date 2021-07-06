import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from '../address.service';
import { Address } from '../Entities/Address';

@Component({
  selector: 'app-update-address',
  templateUrl: './update-address.component.html',
  styleUrls: ['./update-address.component.css']
})
export class UpdateAddressComponent implements OnInit {
  address:Address = new Address();
  id:number = 0;
  constructor(private service: AddressService,private route:ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getAddress(this.id).subscribe(data=>{
      this.address = data;
      console.log(data);
    });
  }

  onSubmit(){
    this.service.updateAddress(this.address,this.id).subscribe(data=>{
      this.router.navigate(["/addresses"]);
    },error=>console.error(error)
    );
  }

}
