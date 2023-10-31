package com.kakaopay.task.settlement.domain;


import lombok.Getter;
import org.springframework.data.util.Streamable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SettlementRequestAggregate implements Streamable<SettlementRequest> {

    private final Streamable<SettlementRequest> streamable;

    @Getter
    private final Map<Long, SettlementRequest> map;

    @Override
    public Iterator<SettlementRequest> iterator() {
        return streamable.iterator();
    }

    private SettlementRequestAggregate(Streamable<SettlementRequest> streamable) {
        this.streamable = streamable;
        this.map = streamable.stream().collect(Collectors.toMap(SettlementRequest::getId, sr -> sr));
    }

    public static SettlementRequestAggregate of(Streamable<SettlementRequest> streamable) {
        return new SettlementRequestAggregate(streamable);
    }

    public List<SettlementRequest> filterByStatus(SettlementRequestStatus status) {
        return Optional.ofNullable(status)
                .map(s -> stream().filter(request -> status == request.getStatus()).toList())
                .orElseGet(this::getList);
    }

    public List<SettlementRequest> getList() {
        return stream().toList();
    }

}
