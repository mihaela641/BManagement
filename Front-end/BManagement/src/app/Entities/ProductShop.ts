import { Product } from "./Product";
import { Shop } from "./Shop";

export class ProductShop{
    public id:number = 0;
    public product:Product = new Product();
    public shop:Shop = new Shop();
    public quantity:number = 1;

    public constructor(
        fields?: {
            id?: number,
            product?: Product,
            shop?: Shop,
            quantity?: number,
        }) {
        if (fields) Object.assign(this, fields);
    }
}