export class Position{
    public id:number = 0;
    public code: string =""; 
    public name: string ='';
    public description: string ='';
    public isDeleted:boolean = false;

    public constructor(
        fields?: {
            id?: number,
            code?: string,
            name?: string,
            description?: string
            isDeleted?: boolean
        }) {
        if (fields) Object.assign(this, fields);
    }
}