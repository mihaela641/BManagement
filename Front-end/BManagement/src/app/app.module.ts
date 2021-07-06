import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { PositionComponent } from './position/position.component';
import { PositionServiceService } from './position-service.service';
import { CreatePositionComponent } from './create-position/create-position.component';
import { FormsModule} from '@angular/forms';
import { UpdatePositionComponent } from './update-position/update-position.component';
import { HomeComponent } from './home/home.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatMenuModule} from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { LayoutModule } from '@angular/cdk/layout';
import { MatSidenavModule } from '@angular/material/sidenav';
import { EmployeeComponent } from './employee/employee.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { TagComponent } from './tag/tag.component';
import { CreateTagComponent } from './create-tag/create-tag.component';
import { UpdateTagComponent } from './update-tag/update-tag.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { CreateManufacturerComponent } from './create-manufacturer/create-manufacturer.component';
import { AddressComponent } from './address/address.component';
import { CreateAddressComponent } from './create-address/create-address.component';
import { OrderComponent } from './order/order.component';
import { UpdateManufacturerComponent } from './update-manufacturer/update-manufacturer.component';
import { UpdateAddressComponent } from './update-address/update-address.component';
import { ShopComponent } from './shop/shop.component';
import { UpdateShopComponent } from './update-shop/update-shop.component';
import { CreateShopComponent } from './create-shop/create-shop.component';
import {MatDialogModule} from '@angular/material/dialog';
import { ManufacturerService } from './manufacturer.service';
import { TypeComponent } from './type/type.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { ProductComponent } from './product/product.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { ProductShopComponent } from './product-shop/product-shop.component';
import { UpdateProductShopComponent } from './update-product-shop/update-product-shop.component';
import { CreateProductShopComponent } from './create-product-shop/create-product-shop.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { RecipeComponent } from './recipe/recipe.component';



@NgModule({
  declarations: [
    AppComponent,
    PositionComponent,
    CreatePositionComponent,
    UpdatePositionComponent,
    HomeComponent,
    EmployeeComponent,
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
    TagComponent,
    CreateTagComponent,
    UpdateTagComponent,
    ManufacturerComponent,
    CreateManufacturerComponent,
    AddressComponent,
    CreateAddressComponent,
    OrderComponent,
    UpdateManufacturerComponent,
    UpdateAddressComponent,
    ShopComponent,
    UpdateShopComponent,
    CreateShopComponent,
    TypeComponent,
    UpdateOrderComponent,
    ProductComponent,
    CreateProductComponent,
    UpdateProductComponent,
    ProductShopComponent,
    UpdateProductShopComponent,
    CreateProductShopComponent,
    CreateOrderComponent,
    RecipeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatListModule,
    MatIconModule,
    MatToolbarModule,
    MatButtonModule,
    LayoutModule,
    MatSidenavModule,
    MatDialogModule
  ],

  providers: [PositionServiceService,ManufacturerService],
  bootstrap: [AppComponent],

})
export class AppModule { }