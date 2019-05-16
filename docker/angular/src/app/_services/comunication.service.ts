import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Description, DivisionResult, AdditionResult, MultiplicationResult, User, SubtractionResult } from '../_models';

@Injectable()
export class ComunicationService {

    private descriptionMessageSource = new BehaviorSubject(new Description());
    private additionResultMessageSource = new BehaviorSubject(new AdditionResult());
    private subtractionResultMessageSource = new BehaviorSubject(new SubtractionResult());
    private multiplicationResultMessageSource = new BehaviorSubject(new MultiplicationResult());
    private divisionResultMassageSource = new BehaviorSubject(new DivisionResult());
    private userMessageSource = new BehaviorSubject(new User());
    userMessage = this.userMessageSource.asObservable();
    DescriptionMessage = this.descriptionMessageSource.asObservable();
    divisionResultMessage = this.divisionResultMassageSource.asObservable();
    multiplicationResultMessage = this.multiplicationResultMessageSource.asObservable();
    additionResultMessage = this.additionResultMessageSource.asObservable();
    subtractionResultMessage = this.subtractionResultMessageSource.asObservable();
    // The constructor :)
    constructor() { }

    changeDescription(description: Description) {
        this.descriptionMessageSource.next(description);
    }

    changeAdditionResult(additionResult: AdditionResult) {
        this.additionResultMessageSource.next(additionResult);
    }

    changeSubtractionResult(subtractionResult: SubtractionResult) {
        this.subtractionResultMessageSource.next(subtractionResult);
    }

    changeMultiplycationResult(multiplicationResult: MultiplicationResult) {
        this.multiplicationResultMessageSource.next(multiplicationResult);
    }

    changeDivisionResult(divisionResult: DivisionResult) {
        this.divisionResultMassageSource.next(divisionResult);
    }

    changeLoggedInUser(user: User) {
        this.userMessageSource.next(user);
    }

}

