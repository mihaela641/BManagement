import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Shop } from '../Entities/Shop';
import { ShopService } from '../shop.service';

@Component({
  selector: 'app-update-shop',
  templateUrl: './update-shop.component.html',
  styleUrls: ['./update-shop.component.css']
})
export class UpdateShopComponent implements OnInit {
  shop:Shop = new Shop();
  id:number = 0;
  constructor(private service:ShopService,private route:ActivatedRoute,private router: Router) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.service.getShop(this.id).subscribe(data=>{
      this.shop = data;
      console.log(data);
    });
  }

  onSubmit(){
    this.service.updateShop(this.shop,this.id).subscribe(data=>{
      this.router.navigate(["/shops"]);
    },error=>console.error(error)
    );
  }
}
