import { Product } from "./Product";
import { Shop } from "./Shop";

export class Order{
    public id:number = 0;
    public code: string = "";
    public product:Product = new Product();
    public shop:Shop = new Shop();
    public quantity:number = 0;
    public status:number = 1;
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            code?: string,
            product?: Product,
            shop?: Shop,
            quantity?: number,
            status?: number,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}