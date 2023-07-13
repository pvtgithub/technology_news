/*
 * Copyright(c) 2023 Luvina Software Company
 *
 * user.utils.ts , 6/30/2023 11:13 AM, LA31-AM-ThoPV
 */


/**
 * Support features for the employee class
 * 
 * @author [ThoPV]
 */
export class UserUtils {
    /**
     * Removes leading and trailing white spaces from a given string.
     * 
     * @param str The input string to be processed.
     * @returns The modified string with no leading or trailing white spaces.
     */
    static removeWhiteSpace(str: string): string {
        return str.trim();
    }

    /**
      * Replaces the percentage sign (%) with its URL-encoded equivalent (%25) in a given string.
      * 
      * @param input The input string to be processed.
      * @returns The modified string with all percentage signs replaced by %25.
     */
    static replacePercent(input: string): string {
        if (input.includes('%')) {
            return input.replace(/%/g, '%25');
        }
        return input;
    }

    /**
    * Replaces the URL-encoded percentage sign (%25) with its actual representation (%) in a given string.
    * @param input The input string to be processed.
    * @returns The modified string with all %25 replaced by %.
     */
    static returnStrInitPercent(input: string) {
        if (input.includes('%25')) {
            return input.replace(/%25/g, '%')
        }
        return input;
    }
}