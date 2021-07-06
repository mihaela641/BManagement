export class Recipe{
    public id:number = 0;
    public description: string ="";
    public name:string="";
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?:number,
            description?:string,
            name?:string,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}