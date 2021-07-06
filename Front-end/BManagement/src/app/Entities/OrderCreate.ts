import { Product } from "./Product";
import { Shop } from "./Shop";

export class OrderCreate{
    public id:number = 0;
    public product:Product = new Product();
    public shop:Shop = new Shop();
    public status:number = 1;

    public constructor(
        fields?: {
            id?: number,
            product?: Product,
            shop?: Shop,
            status?: number,
        }) {
        if (fields) Object.assign(this, fields);
    }
}