package ru.academits.orlov.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && number <= to;
    }

    public Range getRangesIntersection(Range otherRange) {
        double otherFrom = otherRange.getFrom();
        double otherTo = otherRange.getTo();

        if (to <= otherFrom || otherTo <= from) {
            return null;
        }

        if (from <= otherFrom) {
            if (to >= otherTo) {
                return otherRange;
            }

            return new Range(otherFrom, to);
        } else {
            if (otherTo < to) {
                return new Range(from, otherTo);
            }
        }

        return this;
    }

    public Range[] getRangesUnion(Range otherRange) {
        double otherFrom = otherRange.getFrom();
        double otherTo = otherRange.getTo();

        if (from <= otherFrom) {
            if (otherTo <= to) {
                return new Range[]{this};
            }

            if (otherFrom <= to) {
                return new Range[]{new Range(from, otherTo)};
            }
        } else {
            if (to < otherTo) {
                return new Range[]{otherRange};
            }

            if (from <= otherTo) {
                return new Range[]{new Range(otherFrom, to)};
            }
        }

        return new Range[]{this, otherRange};
    }

    public Range[] getRangesSubtraction(Range otherRange) {
        double otherFrom = otherRange.getFrom();
        double otherTo = otherRange.getTo();

        if (from == otherFrom) {
            if (otherTo < to) {
                return new Range[]{new Range(otherTo, to)};
            }

            return null;
        }

        if (from < otherFrom) {
            if (otherTo < to) {
                return new Range[]{new Range(from, otherFrom), new Range(otherTo, to)};
            }

            if (otherFrom < to) {
                return new Range[]{new Range(from, otherFrom)};
            }
        } else {
            if (to <= otherTo) {
                return null;
            }

            if (from < otherTo) {
                return new Range[]{new Range(otherTo, to)};
            }
        }

        return new Range[]{this};
    }
}
