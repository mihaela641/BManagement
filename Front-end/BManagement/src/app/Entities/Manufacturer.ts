import { Address } from "./Address";

export class Manufacturer{
    
    public id:number = 0;
    public name:string ="";
    public address:Address = new Address();
    public foundationDate:Date = new Date();
    public description:string = "";
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            name?: string,
            address?:Address,
            foundationDate?:Date,
            description?: string,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}