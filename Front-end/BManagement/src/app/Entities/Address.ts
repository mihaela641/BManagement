import { Country } from "./Country";

export class Address{
    [x: string]: any;
    public id:number = 0;
    public street:string = "";
    public country:Country = new Country();
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            street?: string,
            country?: Country,
            isDeleted?:boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}