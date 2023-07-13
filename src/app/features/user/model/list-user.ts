/*
 * Copyright(c) 2023 Luvina Software Company
 *
 * ListUser.component.ts , 6/30/2023 11:13 AM, LA31-AM-ThoPV
 */

import { User } from "./user";

/**
 * Represents a ListUser.
 * 
 * @author [ThoPV]
 */
export class ListUser {
    code?: number;
    totalRecord: number = 0;
    employees : User[] = [];
}
