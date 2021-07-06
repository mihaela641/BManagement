import { Product } from "./Product";
import { Recipe } from "./Recipe";
import { Shop } from "./Shop";


export class ProductRecipe{
    public id:number = 0;
    public recipe:Recipe = new Recipe;
    public shop:Shop = new Shop;
    public product:Product = new Product;
    public isDeleted:boolean = false;
    public productQuantity:number = 0;
    public constructor(
        fields?: {
            id?: number,
            recipe?: Recipe,
            shop?:Shop,
            product?:Product,
            isDeleted?: boolean,
            productQuantity?:number
        }) {
        if (fields) Object.assign(this, fields);
    }
}