import { Product } from "./Product";
import { Shop } from "./Shop";

export class Report{
    public id:number = 0;
    public shop:Shop = new Shop();
    public product:Product = new Product();
    public quantity:number = 0;
    public date:Date = new Date();
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            shop?: Shop,
            product?: Product,
            quantity?: number,
            date?:Date,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}