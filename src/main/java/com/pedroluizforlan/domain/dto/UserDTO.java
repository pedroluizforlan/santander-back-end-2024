package com.pedroluizforlan.domain.dto;


import com.pedroluizforlan.domain.model.Account;
import com.pedroluizforlan.domain.model.User;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record UserDTO(Long id,
                      String name,
                      AccountDTO account,
                      CardDTO card,
                      List<FeatureDTO> features,
                      List<NewsDTO> news) {

    public UserDTO(User model) {
        this(
                model.getId(),
                model.getName(),
                Optional.ofNullable(model.getAccount())
                        .map(a-> new AccountDTO(a))
                        .orElse(null),
                Optional.ofNullable(model.getCard())
                        .map(card -> new CardDTO(card))
                        .orElse(null),
                Optional.ofNullable(model.getFeatures())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(feature -> new FeatureDTO(feature))
                        .collect(Collectors.toList()),
                Optional.ofNullable(model.getNews())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(n -> new NewsDTO(n))
                        .collect(Collectors.toList())
                );
    }

    public User toModel(){
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setAccount(Optional
                .ofNullable(this.account)
                .map(AccountDTO::toModel)
                .orElse(null));
        model.setCard(Optional
                .ofNullable(this.card)
                .map(CardDTO::toModel)
                .orElse(null));
        model.setFeatures(Optional
                .ofNullable(this.features)
                .orElse(Collections.emptyList())
                .stream()
                .map(FeatureDTO::toModel)
                .collect(Collectors.toList()));
        model.setNews(Optional
                .ofNullable(this.news)
                .orElse(Collections.emptyList())
                .stream()
                .map(NewsDTO::toModel)
                .collect(Collectors.toList()));
        return model;
    }
}
