import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddressComponent } from './address/address.component';
import { CreateAddressComponent } from './create-address/create-address.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { CreateManufacturerComponent } from './create-manufacturer/create-manufacturer.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { CreatePositionComponent } from './create-position/create-position.component';
import { CreateProductShopComponent } from './create-product-shop/create-product-shop.component';
import { CreateProductComponent } from './create-product/create-product.component';
import { CreateShopComponent } from './create-shop/create-shop.component';
import { CreateTagComponent } from './create-tag/create-tag.component';
import { EmployeeComponent } from './employee/employee.component';
import { HomeComponent } from './home/home.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { OrderComponent } from './order/order.component';
import { PositionComponent } from './position/position.component';
import { ProductRecipeComponent } from './product-recipe/product-recipe.component';
import { ProductShopComponent } from './product-shop/product-shop.component';
import { ProductComponent } from './product/product.component';
import { RecipeComponent } from './recipe/recipe.component';
import { ShopComponent } from './shop/shop.component';
import { TagComponent } from './tag/tag.component';
import { TypeComponent } from './type/type.component';
import { UpdateAddressComponent } from './update-address/update-address.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { UpdateManufacturerComponent } from './update-manufacturer/update-manufacturer.component';
import { UpdateOrderComponent } from './update-order/update-order.component';
import { UpdatePositionComponent } from './update-position/update-position.component';
import { UpdateProductShopComponent } from './update-product-shop/update-product-shop.component';
import { UpdateProductComponent } from './update-product/update-product.component';
import { UpdateShopComponent } from './update-shop/update-shop.component';
import { UpdateTagComponent } from './update-tag/update-tag.component';


const routes: Routes = [ 
{path: 'home', component: HomeComponent},
{path: 'positions', component: PositionComponent},
{path: '', redirectTo: 'home', pathMatch: 'full'},
{path: 'create-position', component: CreatePositionComponent},
{path: 'update-position/:id', component: UpdatePositionComponent },
{path: 'employees', component: EmployeeComponent},
{path: 'create-employee', component: CreateEmployeeComponent},
{path: 'update-employee/:id', component:UpdateEmployeeComponent},
{path: 'tags', component: TagComponent},
{path: 'update-tag/:id', component: UpdateTagComponent},
{path: 'create-tag', component: CreateTagComponent},
{path: 'manufacturers', component: ManufacturerComponent},
{path: 'create-manufacturer', component: CreateManufacturerComponent},
{path: 'update-manufacturer/:id', component: UpdateManufacturerComponent},
{path: 'addresses', component: AddressComponent},
{path: 'create-address',component: CreateAddressComponent},
{path: 'update-address/:id', component: UpdateAddressComponent},
{path: 'orders',component: OrderComponent},
{path: 'shops', component: ShopComponent},
{path: 'update-shop/:id', component: UpdateShopComponent},
{path: 'create-shop', component:CreateShopComponent},
{path: 'types', component: TypeComponent},
{path: 'update-order/:id', component: UpdateOrderComponent},
{path:'products',component: ProductComponent},
{path: 'create-product',component:CreateProductComponent},
{path: 'update-product/:id',component: UpdateProductComponent},
{path: 'productsShops', component: ProductShopComponent},
{path: 'create-productShop', component:CreateProductShopComponent},
{path: 'update-productShop/:id',component:UpdateProductShopComponent},
{path: 'create-order',component:CreateOrderComponent},
{path: 'recipes' , component:RecipeComponent},
{path: 'product-recipe', component:ProductRecipeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
