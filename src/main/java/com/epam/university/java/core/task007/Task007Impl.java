package com.epam.university.java.core.task007;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Task007Impl implements Task007 {

    /**
     * Multiply polynomials. Each collection contains coefficients near i-th member, ex:
     * <p>
     * 3x^3 + 2x^2 - 5x corresponds to collection [3, 2, -5, 0]
     * </p>
     * <p>
     * Task is to multiply two polynomials: ex:
     * </p>
     * <p>
     * (3x^3 + 2x^2 - 5x) * (4x^4 + 2x^2) == multiplyPolynomial([3, 2, -5, 0], [4, 0, 2, 0, 0])
     * </p>
     * <p>
     * If polynomial is degenerating to zero, return [0]
     * </p>
     *
     * @param first  collection with coefficients near-th member of first polynomial
     * @param second collection with coefficients near-th member of second polynomial
     * @return collection of members in multiplied polynomials
     */
    @Override
    public Collection<Integer> multiplyPolynomial(Collection<Integer> first,
                                                  Collection<Integer> second) {
        List<Integer> myFirst = new ArrayList<>(first);
        List<Integer> mySecond = new ArrayList<>(second);
        List<Integer> result = new ArrayList<>(Collections.nCopies(second.size() + first.size()
                - 1, 0));
        for (int i = 0; i < first.size(); i++) {
            for (int j = 0; j < second.size(); j++) {
                result.set(j + i, result.get(j + i) + mySecond.get(j) * myFirst.get(i));
            }
        }
        return result;
    }
}
