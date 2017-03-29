package com.frame.service.front.impl;

import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.core.dao.GeneralDao;
import com.frame.entity.media.MessageEntity;
import com.frame.entity.media.ReplyEntity;
import com.frame.model.dto.media.MessageDTO;
import com.frame.model.dto.media.ReplyDTO;
import com.frame.model.vo.media.MessageVO;
import com.frame.model.vo.media.ReplyVO;
import com.frame.service.front.MessageService;

/**
 * @author wowson
 * @version create time：2017年3月21日 下午7:26:50 类说明 ：
 */
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	GeneralDao dao;

	@Override
	public void saveMsg(MessageEntity entity) {
		dao.getHibernateTemplate().save(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectMsg(MessageDTO dto) {
		Long albumId = dto.getAlbumId();
		int firstResult = (int) ((dto.getPage()-1)*dto.getPageSize());
		int maxResult = (int) (dto.getPageSize()*1);
		String hql = "select m.id as messageId, m.content as content,m.createDateTime as createTime, u.nickName as userName,u.id as userId from MessageEntity m join m.user u where m.albumId = ?  ";
		List<MessageVO> list = (List<MessageVO>) dao.getSessionFactory().getCurrentSession().createQuery(hql).setLong(0, albumId).setResultTransformer(Transformers.aliasToBean(MessageVO.class))
				.setFirstResult(firstResult).setMaxResults(maxResult).list();
		if (list.size() > 0) {
			for (int i = 0; i<list.size(); i++) {
				ReplyDTO replyDTO = new ReplyDTO();
				replyDTO.setMessageId(list.get(i).getMessageId());
				replyDTO.setAlbumId(dto.getAlbumId());
				selectReply(replyDTO);
				list.get(i).setReplyVO(replyDTO.getReply());
			}
		}
		Long totalCount = countMsg(albumId);
		dto.setTotalCount(totalCount);
		dto.setTotalPage((totalCount + dto.getPageSize()-1)/dto.getPageSize());
		dto.setList(list);
	}

	public Long countMsg(Long albumId) {
		return dao.getUnique("select count(*) from MessageEntity where 1=1 and albumId = ? ", albumId);
	}

	@SuppressWarnings("unchecked")
	public void selectReply(ReplyDTO replyDTO) {
		String hql = "select r.content as replyContent,r.createDateTime as replyTime ,u.nickName as replyUserName from ReplyEntity r join r.user u where  r.messageId = ? ";
		List<ReplyVO> list = dao.getSessionFactory().getCurrentSession().createQuery(hql).setParameter(0, replyDTO.getMessageId())
				.setResultTransformer(Transformers.aliasToBean(ReplyVO.class)).list();
		replyDTO.setReply(list);
	}
	
	public void saveReply(ReplyEntity entity){
		dao.getHibernateTemplate().save(entity);
	}
}
