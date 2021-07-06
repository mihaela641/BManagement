export class Tag{
    public id:number = 0;
    public code:string = "";
    public name:string = "";
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            code?: string,
            name?: string,
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}