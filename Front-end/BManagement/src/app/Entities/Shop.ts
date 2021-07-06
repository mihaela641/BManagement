import { Address } from "./Address";

export class Shop{
    public id:number = 0;
    public name:string = "";
    public address:Address = new Address();
    public phoneNumber:string = "";
    public status:number = 1;
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            name?: string,
            address?:Address,
            phoneNumber?:string,
            status?:number,
            isDeleted?:boolean
        }) {
        if (fields) Object.assign(this, fields);
    }

}