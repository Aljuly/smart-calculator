import { Injectable } from '@angular/core';

@Injectable()
export class Formatter {
    // Utility function that forms string from given char of given length
    public assemblyString(numberOfSymbols: number, symbol: string) {
        if (numberOfSymbols === 0) { return ''; }
        let result = '';
        for (let i = 0; i < numberOfSymbols; i++) {
            result += symbol;
        }
        return result;
    }
    // formatting fraction with periodic part
    public formatFraction(fraction: string, divisor: number): string {
        let denominator: number;
        if (fraction.length === 0) { return ''; }
        fraction = fraction.concat(' ');
        if (divisor % 2 === 0) {
            for (let nines = 1; nines < fraction.length; nines++) {
                for (let zeros = 1; zeros + nines < fraction.length - nines; zeros++) {
                    denominator = Number(this.assemblyString(nines, '9')
                    .concat(this.assemblyString(zeros, '0')));
                    if (denominator % divisor === 0) {
                        return '.'.concat(fraction.substring(0, zeros),
                        '(', fraction.substring(zeros, zeros + nines), ')');
                    }
                }
            }
        } else {
            for (let nines = String(divisor).length; nines < fraction.length; nines++) {
                denominator = Number(this.assemblyString(nines, '9'));
                if (denominator % divisor === 0) {
                    return '.'.concat('(', fraction.substring(0, nines), ')');
                }
            }
        }
        return '.'.concat(fraction.substring(0, fraction.length - 1));
    }
}
