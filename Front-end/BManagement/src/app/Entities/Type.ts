export class Type{
    public id:number = 0;
    public code:string = "";
    public name:string = "";
    public description:string = "";
    public mainType:any;
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            code?: string,
            name?: string,
            description?: string,
            mainType:Type,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}