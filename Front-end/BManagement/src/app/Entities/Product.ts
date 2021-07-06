import { Manufacturer } from "./Manufacturer";
import { Type } from "./Type";

export class Product{
    public id:number = 0;
    public name:string = "";
    public type:Type = new Type();
    public price:number = 0;
    public dateAdded:Date = new Date();
    public manufacturer:Manufacturer = new Manufacturer();
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            name?: string,
            type?:Type,
            price?:number,
            dateAdded?:Date,
            manufacturer?:Manufacturer,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}