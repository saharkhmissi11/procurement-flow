import { Role } from "./Role";

export interface Employee{
    id:number;
    firstname: string;
    lastname: string;
    createdAt:string;
    position: string;
    password:string;
    role:Role;
    email:string;
    phone:string;
    imageURL:string;

}

