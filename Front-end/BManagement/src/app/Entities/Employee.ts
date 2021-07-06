import { Country } from "./Country";
import { Position } from "./Position";
import { Shop } from "./Shop";

export class Employee{
    public id:number = 0;
    public name:string ="";
    public birthDate:Date = new Date();
    public position:Position = new Position();
    public country:Country = new Country();
    public salary:number = 0;
    public shop:Shop = new Shop();
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            name?: string,
            birthDate?:Date,
            positionId?:number,
            countryId?: number,
            salary?: number,
            shopId?:number,
            isDeleted:boolean
            
        }) {
        if (fields) Object.assign(this, fields);
    }
}