export class Country{
    public id:number = 0;
    public code:string = "";
    public name:string = "";
    

    public constructor(
        fields?: {
            id?: number,
            code?:string,
            name?: string,
        }) {
        if (fields) Object.assign(this, fields);
    }
}