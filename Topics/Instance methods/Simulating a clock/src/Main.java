class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        if (minutes < 59){
            minutes++;
        }else if (minutes == 59 && hours == 12){
            this.minutes = 0;
            this.hours = 1;
        }else if (minutes == 59 && hours < 12){
            minutes = 0;
            hours++;
        }
    }
}